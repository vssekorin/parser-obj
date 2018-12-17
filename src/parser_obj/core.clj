(ns parser-obj.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn- str->int [str]
  (if (str/blank? str) nil (Integer/parseInt str)))

(defn- str->double [str] (Double/parseDouble str))

(defn- f-element [item]
  (zipmap [:v :vt :vn] (map str->int (str/split item #"/"))))

(defn- vertex-data [keys arr]
  (zipmap keys (map str->double arr)))

(defn- create [type arr]
  (case type
    :f  (map f-element arr)
    :v  (vertex-data [:x :y :z] arr)
    :vt (vertex-data [:u :v :w] arr)
    :vn (vertex-data [:x :y :z] arr)
    :vp (vertex-data [:u :v :w] arr)))

(defn- line-has-data? [line]
  (not (or (str/blank? line) (str/starts-with? line "#"))))

(defn- lines-with-data [filename]
  (filter line-has-data? (line-seq (io/reader filename))))

(defn- add-item [m [first & remaining]]
  (let [type (keyword first)]
    (update m type conj (create type remaining))))

(defn parse [filename]
  (->>
    (lines-with-data filename)
    (map #(str/split % #"\s+"))
    (reduce add-item {:v '[] :f '[] :vt '[] :vn '[] :vp '[]})))

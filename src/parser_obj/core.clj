(ns parser-obj.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [parser-obj.item :as item]))

(defn- line-has-data? [line]
  (not (or (str/blank? line)
           (str/starts-with? line "#")
           (str/starts-with? line "g")
           (str/starts-with? line "s"))))

(defn- lines-with-data [filename]
  (filter line-has-data? (line-seq (io/reader filename))))

(defn- add-item [m data]
  (let [[first & remaining] data
        type (keyword first)]
    (update m type conj (item/create type remaining))))

(defn parse [filename]
  (->>
    (lines-with-data filename)
    (map #(str/split % #"\s+"))
    (reduce add-item {:v  '[] :f  '[] :vt '[] :vn '[] :vp '[]})))

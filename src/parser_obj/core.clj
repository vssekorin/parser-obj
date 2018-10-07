(ns parser-obj.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [parser-obj.item :as item]))

(def ^:private model
  {:v  '[]
   :f  '[]
   :vt '[]
   :vn '[]
   :vp '[]})

(defn- file-lines [filename]
  (line-seq (io/reader filename)))

(defn- lines-with-data [filename]
  (->>
    (file-lines filename)
    (filter not-empty)
    (filter #(not (str/starts-with? % "#")))
    (filter #(not (str/starts-with? % "g")))
    (filter #(not (str/starts-with? % "s")))))

(defn- add-item [m data]
  (let [[first & remaining] data
        type (keyword first)]
    (update m type conj (item/create type remaining))))

(defn parse [filename]
  (->>
    (lines-with-data filename)
    (map #(str/split % #"\s+"))
    (reduce add-item model)))

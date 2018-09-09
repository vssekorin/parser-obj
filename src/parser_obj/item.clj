(ns parser-obj.item
  (:require [clojure.string :as str]))

(defn str->int [str]
  (if (str/blank? str)
    nil
    (Integer/parseInt str)))

(defn- str->double [str]
  (Double/parseDouble str))

(defn- v [x y z]
  {:x x
   :y y
   :z z})

(defn- f [v vt vn]
  {:v v
   :vt vt
   :vn vn})

(defn- v-item [arr]
  (let [[x y z] arr]
    (v (str->double x)(str->double y)(str->double z))))

(defn- f-element [item]
  (let [[v vt vn] (str/split item #"/")]
    (println "!" item)
    (println "!!" v "-" vt "-" vn " = " (str->int v))
    (f (str->int v) (str->int vt) (str->int vn))))

(defn- f-item [arr]
  (let [[first second third] arr]
    (println first "-" second "-" third)
    [(f-element first)(f-element second)(f-element third)]))

(defn create [type arr]
  (case type
    :v (v-item arr)
    :f (f-item arr)))

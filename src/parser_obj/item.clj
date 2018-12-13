(ns parser-obj.item
  (:require [clojure.string :as str]))

(defn- to-map [[k1 k2 k3] [first second third]]
    {k1 first k2 second k3 third})

(defn- str->int [str]
  (if (str/blank? str) nil (Integer/parseInt str)))

(defn- str->double [str] (Double/parseDouble str))

(defn- f-element [item]
  (zipmap [:v :vt :vn] (map str->int (str/split item #"/"))))

(defn- vertex-data [keys arr]
  (to-map keys (map str->double arr)))

(defn create [type arr]
  (case type
    :f  (map f-element arr)
    :v  (vertex-data [:x :y :z] arr)
    :vt (vertex-data [:u :v :w] arr)
    :vn (vertex-data [:x :y :z] arr)
    :vp (vertex-data [:u :v :w] arr)))

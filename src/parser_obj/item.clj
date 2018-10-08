(ns parser-obj.item
  (:require [clojure.string :as str]))

(defn- to-map [k1 k2 k3 arr]
  (let [[first second third] arr]
    {k1 first k2 second k3 third}))

(defn- str->int [str]
  (if (str/blank? str)
    nil
    (Integer/parseInt str)))

(defn- str->double [str] (Double/parseDouble str))

(defn- f-element [item]
  (let [[v vt vn] (str/split item #"/")]
    {:v (str->int v) :vt (str->int vt) :vn (str->int vn)}))

(defn- parse-f [arr]
  (let [[first second third] arr]
    [(f-element first) (f-element second) (f-element third)]))

(defn- double-item-3 [arr]
  (let [[first second third] arr]
    [(str->double first) (str->double second) (str->double third)]))

(defn create [type arr]
  (case type
    :f  (parse-f arr)
    :v  (to-map :x :y  :z  (double-item-3 arr))
    :vt (to-map :u :v  :w  (double-item-3 arr))
    :vn (to-map :x :y  :z  (double-item-3 arr))
    :vp (to-map :u :v  :w  (double-item-3 arr))))

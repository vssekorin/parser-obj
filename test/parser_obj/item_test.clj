(ns parser-obj.item-test
  (:require [clojure.test :refer :all]
            [parser-obj.item :refer :all]))

(deftest to-map-test
  (testing "to map"
    (let [fun #'parser-obj.item/to-map]
      (is (= {:a 1 :b 2 :c 3} (fun [:a :b :c] [1 2 3]))))))

(deftest str->double-test
  (let [fun #'parser-obj.item/str->double]
    (is (= Double (type (fun "0"))))
    (is (= 1.23 (fun "1.23")))))

(deftest str->int-test
  (let [fun #'parser-obj.item/str->int]
    (is (= Integer (type (fun "123"))))
    (is (= 123 (fun "123")))))

(deftest f-element-test
  (let [fun #'parser-obj.item/f-element]
    (is (= {:v 1 :vt 2 :vn 3} (fun "1/2/3")))
    (is (= {:v 4 :vt 5} (fun "4/5")))
    (is (= {:v 6 :vt nil :vn 7} (fun "6//7")))
    (is (= {:v 8} (fun "8")))))

(deftest vertex-data-test
  (let [fun #'parser-obj.item/vertex-data]
    (is {:a 1.2 :b 3.4 :c 5.6} (fun [:a :b :c] ["1.2" "3.4" "5.6"]))))

(deftest create-test
  (is (= (list {:v 1 :vt 2 :vn 3} {:v 4 :vt 5 :vn 6} {:v 7 :vt 8 :vn 9})
         (create :f ["1/2/3" "4/5/6" "7/8/9"])))
  (is (= {:x 1.2 :y 2.3 :z 3.4} (create :v ["1.2" "2.3" "3.4"])))
  (is (= {:u 1.2 :v 2.3 :w 3.4} (create :vt ["1.2" "2.3" "3.4"])))
  (is (= {:x 1.2 :y 2.3 :z 3.4} (create :vn ["1.2" "2.3" "3.4"])))
  (is (= {:u 1.2 :v 2.3 :w 3.4} (create :vp ["1.2" "2.3" "3.4"]))))
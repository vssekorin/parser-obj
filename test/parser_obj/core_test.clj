(ns parser-obj.core-test
  (:require [clojure.test :refer :all]
            [parser-obj.core :refer :all]))

(deftest str->double-test
  (let [fun #'parser-obj.core/str->double]
    (is (= Double (type (fun "0"))))
    (is (= 1.23 (fun "1.23")))))

(deftest str->int-test
  (let [fun #'parser-obj.core/str->int]
    (is (= Integer (type (fun "123"))))
    (is (= 123 (fun "123")))))

(deftest f-element-test
  (let [fun #'parser-obj.core/f-element]
    (is (= {:v 1 :vt 2 :vn 3} (fun "1/2/3")))
    (is (= {:v 4 :vt 5} (fun "4/5")))
    (is (= {:v 6 :vt nil :vn 7} (fun "6//7")))
    (is (= {:v 8} (fun "8")))))

(deftest vertex-data-test
  (let [fun #'parser-obj.core/vertex-data]
    (is {:a 1.2 :b 3.4 :c 5.6} (fun [:a :b :c] ["1.2" "3.4" "5.6"]))))

(deftest create-test
  (let [fun #'parser-obj.core/create]
    (is (= (list {:v 1 :vt 2 :vn 3} {:v 4 :vt 5 :vn 6} {:v 7 :vt 8 :vn 9})
           (fun :f ["1/2/3" "4/5/6" "7/8/9"])))
    (is (= {:x 1.2 :y 2.3 :z 3.4} (fun :v ["1.2" "2.3" "3.4"])))
    (is (= {:u 1.2 :v 2.3 :w 3.4} (fun :vt ["1.2" "2.3" "3.4"])))
    (is (= {:x 1.2 :y 2.3 :z 3.4} (fun :vn ["1.2" "2.3" "3.4"])))
    (is (= {:u 1.2 :v 2.3 :w 3.4} (fun :vp ["1.2" "2.3" "3.4"])))))

(deftest line-has-data?-test
  (testing "line has data?"
    (let [fun #'parser-obj.core/line-has-data?]
      (is (fun "1 2 3"))
      (is (not (fun "")))
      (is (not (fun " ")))
      (is (not (fun nil))))))

(deftest parse-test
  (testing "parse"
    (let [result (parse "test2.obj")]
      (is (map? result))
      (is (contains? result :v))
      (is (contains? result :f))
      (is (contains? result :vn))
      (is (contains? result :vt))
      (is (contains? result :vp)))))

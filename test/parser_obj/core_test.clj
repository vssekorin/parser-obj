(ns parser-obj.core-test
  (:require [clojure.test :refer :all]
            [parser-obj.core :refer :all]))

(deftest line-has-data?-test
  (testing "line has data?"
    (let [fun #'parser-obj.core/line-has-data?]
      (is (fun "1 2 3"))
      (is (not (fun "")))
      (is (not (fun " ")))
      (is (not (fun nil)))
      (is (not (fun "# test")))
      (is (not (fun "g 123")))
      (is (not (fun "s 123"))))))

(deftest parse-test
  (testing "parse"
    (let [result (parse "test2.obj")]
      (is (map? result))
      (is (contains? result :v))
      (is (contains? result :f))
      (is (contains? result :vn))
      (is (contains? result :vt))
      (is (contains? result :vp)))))

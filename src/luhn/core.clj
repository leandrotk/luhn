(ns luhn.core
  (:gen-class))

(defn remove-hyphen [str]
  (clojure.string/replace str "-" ""))

(defn seq-char->int [seq-char]
  ((comp #(- % 48) int) seq-char))

(ns luhn.core
  (:gen-class))

(defn remove-hyphen [str]
  (clojure.string/replace str "-" ""))

(defn seq-char->int [seq-char]
  ((comp #(- % 48) int) seq-char))

(defn string->ints [str]
  (->> str
       clojure.string/trim
       remove-hyphen
       (map seq-char->int)))

(defn double-two-by-two [ints]
  (let [reversed-ints (reverse ints)]
    (loop [doubled-ints (list (first reversed-ints))
           [first second & remaining] (rest reversed-ints)]
      (if first
        (recur (remove nil? (conj doubled-ints (* 2 first) second)) remaining)
        doubled-ints))))

(defn digits [int]
  (map #(Character/digit % 10) (str int)))

(defn sum-all-digits [ints]
  (->> ints
       (map digits)
       flatten
       (reduce +)))

(defn luhn-check? [int]
  (= (mod int 10) 0))

(defn luhn? [credit-card-number]
  (->> credit-card-number
       string->ints
       double-two-by-two
       sum-all-digits
       luhn-check?))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
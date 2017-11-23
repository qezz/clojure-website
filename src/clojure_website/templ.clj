(ns clojure-website.templ)

;; (deftype OptionalValue [value default-value])
;; (defmulti set-value class)
;; (defmethod set-value OptionalValue [value]
;; (OptionalValue. value (.default-value

(def default-values
  {:username "%USERNAME%"})

(defn compile-template-internal [defined-values-dict template]
  (if (not (empty? defined-values-dict))
    (let [keyval (first defined-values-dict)
          key (first keyval)
          value (if (string? (second keyval))
                  (second keyval)
                  (str (second keyval))
                  )
          str-to-replace (str "\\{\\{" (name key) "\\}\\}")]
      (recur (rest defined-values-dict)
             (clojure.string/replace template (re-pattern str-to-replace) value)))
    template))


(defn compile-template [defined-values-dict template]
  (println default-values "<-" defined-values-dict)
  (println "Compile...")
  (compile-template-internal (merge default-values defined-values-dict) template))


;; (#'user/compile-teplate {'username "Sergey"} "template: Hello {{username}}" )

(ns clj-loggly.core
  (:require [clj-http.client :as http])
  (:use [clojure.data.json :only (json-str
                                  write-json
                                  read-json)]))

(def loggly-base-input-url "https://logs.loggly.com/inputs/")

(defn post-event
  [input-key level message]
  (let [loggly-input-url (str loggly-base-input-url
                              input-key)]
    (http/post loggly-input-url
               {"content/type" "text/plain"
                :body (json-str {:level level
                                 :message message})})))

(defn info
  [input-key message]
  (post-event input-key :info message))

(defn debug
  [input-key message]
  (post-event input-key :debug message))

(defn error
  [input-key message]
  (post-event input-key :error message))

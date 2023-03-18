(ns data-retriever.request
  (:gen-class)
(:require [clojure.data.json :as json]
          [monger.core :as mg]
          [monger.collection :as mc]))

(def api_key "RGAPI-2ff986ea-bfe6-4563-a308-11208a946efa")
(def url (str "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/mundicowar?api_key=" api_key))
(defn url-by-name [username] (str "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" username "?api_key=" api_key))

(defn fetch-url [address]
  (with-open [stream (.openStream (java.net.URL. address))]
    (let  [buf (java.io.BufferedReader.
                (java.io.InputStreamReader. stream))]
      (apply str (line-seq buf)))))

(defn get-user [username]
  (json/read-str (fetch-url (url-by-name username))))

(defn print-user []
  (prn (json/read-str (fetch-url url))))

(defn find-it []
  (let [conn (mg/connect)
        db (mg/get-db conn "myNewDB")
        coll "myNewCollection1"]
    (mc/find-maps db coll {:x 3})))

(defn insert-user []
  (let [conn (mg/connect)
        db (mg/get-db conn "myNewDB")
        coll "myNewCollection1"
        user (json/read-str (fetch-url url))]
    (prn user)
    (mc/insert db coll user)))
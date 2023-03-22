(ns data-retriever.request
  (:gen-class)
  (:require [clojure.data.json :as json]
            [monger.core :as mg]
            [monger.collection :as mc]
            [clojure.java.io :as io]
            [clojure.data.codec.base64 :as b64]))

(def api_key "RGAPI-1ad9a141-6af2-4c64-834c-2a1357f8895f")

(def url (str "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/mundicowar?api_key=" api_key))

(defn url-by-name [username] (str "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" username "?api_key=" api_key))
(defn get-matches-url [puuid] (str "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/" puuid "/ids?start=0&count=20" "&api_key=" api_key))
(defn match-url [match-id] 
  (str "https://americas.api.riotgames.com/lol/match/v5/matches/" match-id "?api_key=" api_key))

(def items-url "https://ddragon.leagueoflegends.com/cdn/13.5.1/data/pt_BR/item.json")

(defn item-picture-url [number] 
  (str "https://ddragon.leagueoflegends.com/cdn/13.5.1/img/item/" number ".png"))

(defn user-by-uuid-url [uuid]
  (str "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" uuid "?api_key=" api_key))

(defn fetch-url [address]
  (with-open [stream (.openStream (java.net.URL. address))]
    (let  [buf (java.io.BufferedReader.
                (java.io.InputStreamReader. stream))]
      (apply str (line-seq buf)))))

(defn get-user [username]
  (json/read-str (fetch-url (url-by-name username))))

(defn get-user-by-uuid [uuid]
  (json/read-str (fetch-url (user-by-uuid-url uuid))))

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

(defn get-match-ids [puuid] 
  (json/read-str (fetch-url (get-matches-url puuid))))


(defn get-match [match-id]
  (json/read-str (fetch-url (match-url match-id))))

(defn get-items []
  (json/read-str (fetch-url items-url)))

(defn copy-uri-to-file [uri file]
  (with-open [in (io/input-stream uri)
              out (io/output-stream file)]
    (io/copy in out)))

(defn save-item-image [number]
  (copy-uri-to-file (item-picture-url number) (str "./" number ".png")))

(defn uri->bytes [uri]
  (with-open [in (io/input-stream uri)
              out (java.io.ByteArrayOutputStream.)]
    (io/copy in out)
    (.toByteArray out)))

(defn save-image [number]
  ;; (String. (b64/encode (.getBytes (str number))) "UTF-8")
  (String. (b64/encode (uri->bytes (item-picture-url number))))
  )
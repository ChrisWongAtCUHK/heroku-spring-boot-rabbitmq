# [建立RabbitMQ & SpringBoot集成環境](https://www.tpisoftware.com/tpu/articleDetails/2116)

## 處理 JSON 物件 (更像真實開發)
```
curl "http://localhost:5000/rabbitmq/sendUser?name=Chris&msg=HelloJSON"
```
## 將現有的結構升級為 Fanout Exchange (廣播模式)
```
curl "http://localhost:5000/rabbitmq/sendFanout?name=Chris&msg=HelloEveryone"
```

## 模擬一個日誌或新聞系統
```
curl "http://localhost:5000/rabbitmq/sendTopic?routingKey=hk.news&name=Chris&msg=HK_News_Update"
```
## Deploy on Heroku
```
heroku create spring-boot-rabbitmq-chris  # 名字必須是唯一的，可以加你的名字
git push heroku main
heroku config:set CLOUDAMQP_URL=${CLOUDAMQP_URL}
heroku ps:wait
heroku open
```
```
curl "$(heroku info -s | grep web_url | cut -d= -f2)rabbitmq/sendTopic?routingKey=hk.news&name=Chris&msg=HK_News_Update"
```

```
unalias mqsend 2>/dev/null
```
```
mqsend() {
  # $1 代表你輸入的第一個參數，如果沒輸入則預設為 "QuickTest"
  local msg="${1:-QuickTest}"
  local url=$(heroku info -s | grep web_url | cut -d= -f2)
  
  curl "${url}rabbitmq/sendTopic?routingKey=hk.news&name=Chris&msg=${msg}"
}
```
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
# RabbitMQ 發送函數
mqsend() {
  # 若要相容 sh，建議直接使用變數名（不加 local）或改用 bash
  _input_msg="${1:-QuickTest}"

  # 使用 Python 進行 URL Encoding，確保 $input_msg 內的單引號不會破壞命令
  _encoded_msg=$(python3 -c "import urllib.parse, sys; print(urllib.parse.quote(sys.stdin.read().strip()))" <<< "$_input_msg")

  # 取得 Heroku URL 並移除結尾斜槓以便後續拼接
  _url=$(heroku info -s | grep web_url | cut -d= -f2 | sed 's/\/$//')

  if [ -z "$_url" ]; then
    echo "Error: Could not get Heroku URL."
    return 1
  fi

  echo "Sending to Heroku: $_input_msg"

  # 執行 curl 並確保 URL 完整引用
  curl -s "${_url}/rabbitmq/sendTopic?routingKey=hk.news&name=Chris&msg=${_encoded_msg}"

  echo "\nDone."
}
```

```
mqsend 'abc&123'
```

# Docker
## Build
```
docker build -t spring-boot-rabbitmq .
```
## Run with environment variable
```
docker run -p 8080:8080 -e CLOUDAMQP_URL spring-boot-rabbitmq
```
## Tag and push to Docker Hub
```
docker tag spring-boot-rabbitmq chriswongatcuhk/spring-boot-rabbitmq
docker push chriswongatcuhk/spring-boot-rabbitmq
```
## The Fix: Multi-Platform Build
```
docker build --platform linux/amd64 -t chriswongatcuhk/spring-boot-rabbitmq .
docker push chriswongatcuhk/spring-boot-rabbitmq
```

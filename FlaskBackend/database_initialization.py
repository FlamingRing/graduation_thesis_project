import requests
springbootBackendUrl = "localhost:8888/update_database"
requests.get("http://" + springbootBackendUrl)
print("success")
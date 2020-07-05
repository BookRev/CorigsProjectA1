import requests
import json
import sys
import io

# set up the request parameters
params = {
  'api_key': 'rainforestapi key here',
  'type': 'reviews',
  'amazon_domain': 'amazon.com',
}

# make the http GET request to Rainforest API
def main(aabb):
  sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf8')
  params['gtin'] = int(aabb)
  api_result = requests.get('https://api.rainforestapi.com/request',params)
  ret0 = json.loads(json.dumps(api_result.json()))
  print(ret0["product"]["title"])
  print(ret0["product"]["link"])
  print(ret0["summary"]["rating"])
  print(ret0["product"]["sub_title"]["text"])
  try:
    for i in range(0,6):
      print("review")
      print(ret0["reviews"][i]["title"])
      print(ret0["reviews"][i]["body"])
      print(ret0["reviews"][i]["rating"])
  except Exception as err:
     print(err)

if __name__ == '__main__':
    for i in range(1, len(sys.argv)):
        aabb = sys.argv[i]
        try:
         main(aabb)
        except Exception as err:
          print(sys. exc_info())
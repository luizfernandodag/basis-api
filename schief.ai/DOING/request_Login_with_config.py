import configparser
import json
import os
import sys

#sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))) # PROBLEMA AQUI
import requests
from requests.auth import HTTPBasicAuth

from config.classes import prepare_config

c = prepare_config.getSectionConfigurations('DEV')
print(c)

BASE_URL = c['baseurl']
print(BASE_URL)
USER_NAME = c['username']
SCOPE = c['scope']
PASSWORD = c['password']
ACCESS_TOKEN = c['access_token']
TOKEN_TYPE = c['token_type']
REFRESH_TOKEN = c['refresh_token']


#print("Current working directory:", os.getcwd())

#with open('setting.ini', 'r') as file:
#    print(file.read())

# Initialize the config parser

#scope = config['r']['scope']
#print(scope)
# password = config['r']['password']
# access_token = config['r']['access_token']
# token_type = config['r']['token_type']
# refresh_token = config['r']['refresh_token']

c = prepare_config.getSectionConfigurations('DEV')
#print(c)

BASE_URL = c['baseurl']
USER_NAME = c['username']
SCOPE = c['scope']
PASSWORD = c['password']
ACCESS_TOKEN = c['access_token']
TOKEN_TYPE = c['token_type']
REFRESH_TOKEN = c['refresh_token']


def build_data_request(username:str, password:str, scope:str):
    return {
    "username": username,
    "password": password,
    "scope": scope
    }

def build_headers():
    return {
    # "Content-Type": "multipart/form-data",
    "User-Agent": "insomnia/2023.5.8"
    }

def getResponse(baseurl, requesturl, username, password,scope):
    print(baseurl+requesturl)
    response = requests.post(
         baseurl+requesturl, 
         data=build_data_request(username, password, scope),
         headers= build_headers()
        )
    return response

r = getResponse(BASE_URL, "/login/", USER_NAME, PASSWORD, SCOPE)

if(r.status_code == 200):
    print(r.json())
#     with open("settings2.ini", "w") as file:
#         file.write("[r] ")
#         json.dump(r.json(), file, indent=4)

# print(r.json())

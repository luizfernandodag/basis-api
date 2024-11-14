import json
import os
from configparser import ConfigParser

import requests


def set_env_config():
    p = os.getcwd() +r'\config\settings.ini' # PROBLEMA AQUI
    print("path:"+p)
    config = ConfigParser()
    config.read(p)
    print("sections:")
    print(config.sections())
    return config


def getSectionConfigurations(section:str):
    config = set_env_config()
    #configurations = ''
    configurations = dict(config.items(section))
    '''configurations = {
        'baseurl': config[group]['baseurl'],
        'username': config[group]['username'],
        'scope': config[group]['scope'],
        'password': config[group]['password'],
        'access_token': config[group]['access_token'],
        'token_type': config[group]['token_type'],
        'refresh_token': config[group]['refresh_token'],
    }'''
    return configurations

c = getSectionConfigurations('DEV')


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
    
def re_write_setting(section:str, jsonDict:dict):
    config = ConfigParser()
    
    up_data = {key.upper(): value for key, value in jsonDict.items()}
    config[section] = {
    'BASEURL': 'https://dev.api.schief.ai',
    'USERNAME': 'faketeste@gmail.com',
    'SCOPE': 'admin',
    'PASSWORD': 'senha@teste'
        
    }
    with open("config/settings.ini", "r+") as file:
        config.write(file)
    
    config[section].update(up_data)
    
    with open("config/settings.ini", "r+") as file:
        config.write(file)
        


def getResponse(baseurl, requesturl, username, password,scope):
    response = requests.post(
         baseurl+requesturl, 
         data=build_data_request(username, password, scope),
         headers= build_headers()
        )
    return response

r = getResponse(BASE_URL, "/login/", USER_NAME, PASSWORD, SCOPE)



if(r.status_code == 200):
    #print("r.json")
    #print(r.json())
    
    re_write_setting('DEV', r.json())

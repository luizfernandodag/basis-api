import configparser
from typing import List
from requests.auth import HTTPBasicAuth 
from pydantic import BaseModel, Field
import requests

# Dict com path como chave e valor como bolleano indicando se o path precisa de um path param


class endpoint(BaseModel):
    
    link_endpoint: str
    type:str
    path_var:str= Field(default='')
    
class endpointsList(BaseModel):
    endpoints: List[endpoint]
    
    
    def getEndpoint(self, link: str) -> endpoint|str:
        for end in self.endpoints:
            if(end.link_endpoint == link):
                return end
            
        return ''
    
    
    

endpoints = {
    'v1': {'link_endpoint':'/', 'type': 'POST' },
    'v2': {'link_endpoint':'/confirm_email/', 'type': 'GET', 'path_var': 'token'},
    'v3': {'link_endpoint':'/send_email_confirmation/', 'type': 'GET', 'path_var': 'email'},
    'v4': {'link_endpoint':'/unconfirm/', 'type': 'GET', 'path_var': 'email'},
    'v5': {'link_endpoint':'/reset_password/', 'type': 'GET', 'path_var': 'email'},
    'v6': {'link_endpoint':'/reset_password/', 'type': 'PUT', 'path_var': 'token'},
    'v7': {'link_endpoint':'/create', 'type': 'POST'},
    'v8': {'link_endpoint':'/create/super', 'type': 'POST'},
    'v9': {'link_endpoint':'/create/plan', 'type': 'POST'},
    'v10': {'link_endpoint':'/refresh/', 'type': 'GET', 'path_var': 'token_to_refresh'},
             }

endpoint_instances = [endpoint(**val) for val in endpoints.values() ]
for i in endpoint_instances:
    print(i)
    
l = endpointsList(endpoints=endpoint_instances)
# print(l)
print()
print(l.getEndpoint("/"))
print(l.getEndpoint("/confirm_email/"))


print()
print()
print()
#import os
#print("Current working directory:", os.getcwd())

#with open('setting.ini', 'r') as file:
#    print(file.read())

# Initialize the config parser
config = configparser.ConfigParser()
config.read('settings2.ini')

baseurl = config['r']['baseurl']
username = config['r']['username']
scope = config['r']['scope']
password = config['r']['password']
access_token = config['r']['access_token']
token_type = config['r']['token_type']
refresh_token = config['r']['refresh_token']



# CONFIRM EMAIL request


params = {
    "access_token": access_token,
    "token_type": token_type,
    "refresh_token": refresh_token
    
}
url = baseurl + "/confirm_email/" + params["access_token"]
print(url)
headers = {
    "accept": "application/json"
    #"User-Agent": "insomnia/2023.5.8"
}

response = requests.get(url, headers=headers)

print("CONFIRM_EMAIL")
print(response)



#END




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
    response = requests.post(
         baseurl+requesturl, 
         data=build_data_request(username, password, scope),
         headers= build_headers()
        )
    return response



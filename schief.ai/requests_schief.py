
import os
import sys

import requests
from pydantic import BaseModel, Field

#sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from config.prepare_config import *

#print(os.environ.get('PYTHONPATH', '').split(os.pathsep))

section = 'DEV'

c = getSectionConfigurations(section)
print(c)

BASE_URL = c['baseurl']
print(BASE_URL)
if not BASE_URL.startswith("https://"):
    BASE_URL = f"https://{BASE_URL}"
print(BASE_URL)
USER_NAME = c['username']
SCOPE = c['scope']
PASSWORD = c['password']
ACCESS_TOKEN = c['access_token']
TOKEN_TYPE = c['token_type']
REFRESH_TOKEN = c['refresh_token']


COMMON_HEADER = {
    "User-Agent": "insomnia/2023.5.8"
    #"Authorization": f"{TOKEN_TYPE} {ACCESS_TOKEN}"
}

class ConfirmNameRequest(BaseModel):
    path:str = "/"
    method:str = "GET"
    headers:dict = COMMON_HEADER
    body: dict = {}
    
class CreateUserRequest(BaseModel):
    path:str = "/"
    method:str = "POST"
    headers:dict = {**COMMON_HEADER, "Content-Type": "application/json"}
    body: dict = {
        "first_name": "teste",
        "last_name": "teste",
        "email": "faketeste2@gmail.com",
        "password": "senha@teste"
    }
    
 
 
def getResponse(baseurl, requesturl, username, password,scope):
    response = requests.post(
         baseurl+requesturl, 
         data=build_data_request(username, password, scope),
         headers= build_headers()
        )
    return response

#r = getResponse(baseurl, "/login/", username, password, scope)
 
 
 
 
class LoginRequest(BaseModel):
    path:str = "/login/"
    method:str = "POST"
    headers:dict = {**COMMON_HEADER, "Content-Type": "multipart/form-data"}
    body: dict = {
        "username": "faketeste@gmail.com",
        "password": "senha@teste",
        "scope": "admin",
    }
    
class NewRequest(BaseModel):
    path: str = "/"
    method: str = "POST"
    headers: dict = {**COMMON_HEADER, "Content-Type": "multipart/form-data"}
    body: dict = {
        "first_name": "Ad",
        "last_name": "min",
        "email": "admin@admin.com.br",
        "password": "senha"
    }
    
class RefreshRequest(BaseModel):
    path: str = "/"
    method: str = "POST"
    headers: dict = {**COMMON_HEADER, "Content-Type": "multipart/form-data"}
    body: dict = {}

class SendEmailConfirmationRequest(BaseModel):
    path: str = f"{BASE_URL}/login/"
    method: str = "POST"
    headers: dict = COMMON_HEADER
    body: dict = {}
    
class UnconfirmRequest(BaseModel):
    path: str = f"{BASE_URL}/login/"
    method: str = "POST"
    headers: dict = COMMON_HEADER
    body: dict = {}

def send_request(request_model):
    url = BASE_URL + request_model.path
    print("model path:" + request_model.path)
    print("url:"+url)
    response = requests.request(
        method=request_model.method,
        url=url,
        headers=request_model.headers,
        json=request_model.body
    )
    return response.json()



   
    
    

confirm_name_response = send_request(ConfirmNameRequest())
print("Confirm Name Response:", confirm_name_response)
        
    
create_user_response = send_request(CreateUserRequest())
print("Create User Response:", create_user_response)
l = LoginRequest()
print('path:' + l.path)
login_response = send_request(LoginRequest())
#print("Login Response:", login_response)


# Executa as requisições
#confirm_name_response = send_request(ConfirmNameRequest())
#print("Confirm Name Response:", confirm_name_response)

#create_user_response = send_request(CreateUserRequest())
#print("Create User Response:", create_user_response)

login_response = send_request(LoginRequest())
print("Login Response:", login_response)
'''
new_request_response = send_request(NewRequest())
print("New Request Response:", new_request_response)

refresh_response = send_request(RefreshRequest())
print("Refresh Response:", refresh_response)

send_email_confirmation_response = send_request(SendEmailConfirmationRequest())
print("Send Email Confirmation Response:", send_email_confirmation_response)

unconfirm_response = send_request(UnconfirmRequest())
print("Unconfirm Response:", unconfirm_response)'''
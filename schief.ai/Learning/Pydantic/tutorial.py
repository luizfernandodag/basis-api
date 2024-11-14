from pydantic import BaseModel, EmailStr, field_validator


    

class User(BaseModel):
    name: str
    email: EmailStr
    account_id:int
    
    @field_validator("account_id")
    def validate_account(cls, value):
        if(value <= 0):
            raise ValueError(f"account id must be positive: {value}")
        return value
    
user = User(
    name = "Luiz",
    email="luiz@email.com",
    account_id=1234
) 

user_data = {
    'name': 'Luiz',
    'email': 'luiz@mail.com',
    'account_id': 10
    
}

user = User(**user_data)
print(user)

user_json_str = user.model_dump_json()
print(user_json_str)

user_dict = user.model_dump()
print(user_dict)

user2 = User.model_validate_json(user_json_str)
print(user2)

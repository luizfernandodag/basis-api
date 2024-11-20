from typing import List, Optional
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI()

class Item(BaseModel):
    text: str = None
    id_done: bool = False
    


itens = [] 

@app.get("/")
def root():
    return {"Hello": "World"}

@app.post("/items")
def create_item(item: Item):
    itens.append(item)
    return itens


@app.post("/items/")
def list_item(item: str):
    itens.append(item)
    return itens

@app.get("/items/{id}")#, response_model=Item)
def get_item(id: int) -> Item:
    print("A")
    
    if (id< 0) or id >= len(itens) :
        raise HTTPException(status_code=404, detail="Item not found get item")
    return itens[id]


@app.get("/items/subarray/{limit}")
def list_subarray_items(limit: int) -> List[str]:
    print("C")
    if limit is not None:
        print("not noe")
        if limit < 0 or limit >= len(itens):
            raise HTTPException(status_code=404, detail="Item not found list items")
        return itens[0:limit]  # Retorna o item específico
    else:
        print("else")
    return itens

@app.get("/items/")
def list_items(id: Optional[int] = None) -> List[str]:
    print("B")
    if id is not None:
        if id < 0 or id >= len(itens):
            raise HTTPException(status_code=404, detail="Item not found list itemsaaaa")
        return itens[0:id]  # Retorna o item específico
    return itens
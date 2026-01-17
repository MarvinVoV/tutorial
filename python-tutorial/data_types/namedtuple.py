from typing import NamedTuple

class Address(NamedTuple):
	street: str
	city: str

def get_address(street:str, city:str):
	return Address(street, city)

addr = get_address('123 Main', 'Anytown')
print(addr.city)
 
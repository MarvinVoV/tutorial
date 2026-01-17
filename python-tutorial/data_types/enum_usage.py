from enum import Enum

class UserType(int, Enum):
    VIP = 3
    BANNED = 4
    
    
type = 3
if type == UserType.VIP:
    print("VIP")
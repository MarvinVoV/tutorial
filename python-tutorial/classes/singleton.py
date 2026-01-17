from typing import Self


class Config:
    _instance = None

    def __new__(cls) -> Self:
        if not cls._instance:
            cls._instance = super().__new__(cls)
        return cls._instance

    def say(self):
        print("Hello")


c = Config()
c.say()

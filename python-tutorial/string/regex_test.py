import re

# (?=...)  lookahead assertion
result = re.match('Isaac (?=Asimov)', 'Isaac Asimov')
print(result)

# (?!...)  negative lookahead assertion
result = re.match('Isaac (?!Asimov)', 'Isaac Asimov')
print(result)

pattern = re.compile('[ab]cbd', re.X)
result = pattern.match('acbd')
print(result)


# Border Manager 

### Compatible with: 
    Minecraft 1.19.1
### Tested on: 
    A 1.19.1 paper server

## Usage

Move a jar file of this project into the `plugins` directory and copy in the `BorderManager` directory.
Inside the `BorderManager` directory exist a file titled `borders.brd`. 
Despite the funny extension the file can be edited with a plain text editor like notepad.
<strong>OBSERVE</strong> that the file  follows a strict syntax, and lacks input sanitation.
Each line in the file represents one set of coordinates written in the format: <br>
x<sub>1</sub>,z<sub>1</sub>,x<sub>2</sub>,z<sub>2</sub><br>
Where x<sub>1</sub> < x<sub>2</sub> and z<sub>1</sub> < z<sub>2</sub>.
Without any spaces, negative numbers can be represented by a negative sign(`-`)

## Description
In the `borders.brd` the user specifies the areas he wants to be considered un protected, the areas are rectangular and 
specified as mentioned in Usage. the plugin first runs a check if the block is in the over-world and if so  it then 
checks if the blocks coordinates fit inside the specified area or not. If so then the block may be broken but the item 
drop is cancelled. Note that this also applies to player based blocks and there is no way to only have specific blocks 
be affected (can always be added). The plugins intent was to limit the amount of resources me and my friends have access 
to forcing us to make a sort of economy.

## Licencing

Read LICENCE in root directory

## Contributing

Please be my guest and be respectful in your pull requests

<br>

***Please if you use this just give me some cred, I can really use it*** :)
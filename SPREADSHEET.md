### Omg, I just want a simple spreadsheet for Pat 4!

TLDR: There's a simple spreadsheet(OpenOffice Calc) version [here](https://github.com/rkeeves/p4calc/blob/main/spreadsheet/p4calc.ods).

Bagaluth posted a long time ago a demand calculation/planning spreadsheet.
It was fantastic except for one thing:
it used approximate (based on experiments with the game) values for consumer and industry demand and other things.

I decided to take a look at the ini files (Falko and others posted on the german forums).
In the ini files each and every parameter is modifiable.
Aka there's a way to directly calculate demands without using "magical approx numbers".
I mean the game is deterministic, it has to calculate the demands.
So if we figure out the internals, we can compute them too.
So the spreadsheet is my attempt at recreating the calculations.
These calculations are tailored to my needs, but you can modify it to fit yours.
For example: I set "worker per workshop" globally, but in the ini files you can set it differently for each product.

### What's the point of all of this you ask?

Well, if you don't like the game's economy you can modify it via ini files.
But if you modify the ini files Bagaluth's spreadsheet will not work, because the default settings are "baked" into it,
because he uses these approximate values which they gathered by playing the UNMODDED game.
And this is where this spreadsheet comes into the picture.
(I mean for example there's a multiplier in the ini, which alters demand globally for all products.)

If you are playing a modified game (via modified ini), but you still want accurate demand, 
asset and profit calculations you can use this one.

The other main change from Bagaluth is that product dependencies are not baked into it.
In the ini files you can alter which product needs which ones as ingredients.
So, in the spreadsheet you can change the ingredients (it is represented by a lower triangular matrix), 
and it will recalculate everything.
from PIL import Image,ImageColor,ImageDraw
nameList = ["red","gray","green","black","white","blue","brown"]
nameprefix = "toa_crate_side_"
ogName = nameList[0]
paletteImage = Image.open('palettes.png', mode='r')
image = Image.open(nameprefix + ogName+'.png', mode='r')


def getPallette(x):
    iteratedPalette = []
    for i in range(5):
        #print(paletteImage.getpixel((x,i)))
        iteratedPalette.append(paletteImage.getpixel((x,i)))
   # print("-palette end-")
    return iteratedPalette
paletteOriginal = getPallette(0)
   
width = 16
height = 16
paletteWidth = paletteImage.size[0] 

for palette in range(1,paletteWidth):
    imageNew = image.copy()
    currentPalette = getPallette(palette)
    currentName = nameList[palette]
    pixelMap = imageNew.load()
    for x in range(width):
        for y in range(height):
            currentPixel = pixelMap[x,y]
            if (currentPixel != (255,255,255,0)):
                for j in range(5):
                    #print ("owch!")
                    #print (currentPixel)
                    jPalette = paletteOriginal[j]
                    if (currentPixel == jPalette):
                        #print('hello from the other side')
                        imageNew.putpixel((x,y),currentPalette[j])
            
    imageNew.save(nameprefix + currentName + '.png')


    

        


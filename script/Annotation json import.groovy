// ----------- IMPORT JSON


def gson = GsonTools.getInstance(true)

def imageData = getCurrentImageData()
def name = GeneralTools.getNameWithoutExtension(imageData.getServer().getMetadata().getName())
def pathInput = buildFilePath(PROJECT_BASE_DIR)

def json = new File(pathInput + "/script/segmentations.json").text

// Read the annotations
def type = new com.google.gson.reflect.TypeToken<List<qupath.lib.objects.PathObject>>() {}.getType()
def deserializedAnnotations = gson.fromJson(json, type)

addObjects(deserializedAnnotations)   

println 'Done!'
# Whole-Slide Image Analysis and Quantitative Pathology with QuPath

This is a 2-day workshop focusing on the quantitative analysis of H&E and fluorescence whole-slide images with QuPath.

Participants learn how to annotate regions, to perform stain deconvolution, to segment nuclei (watershed-based method and pretrained deep learning models), to classify pixels and cells (shallow machine learning), to identify fluorescent markers associated with cells (intensity thresholding and shallow machine learning) and to characterize cell sub-populations (region clustering). The first day focuses on H&E and H-DAB whole-slide images. The second day focuses on multiplexed fluorescence whole-slide images.

A pdf introduces pyramidal representation of images and describes the background required to understand the different analyses conducted through the workshop. Links to video tutorials are available in the pdf to see how each "hands on" session can be done. The images used in these sessions are available at https://zenodo.org/record/6391629. The scripts and Stardist model are available in the script folder.

# Video tutorials
[QuPath installation, data and script downloading](https://youtu.be/4RqV4QpYFo0) <br>
[Project creation and annotations](https://youtu.be/vr9w_LYtSso) <br>
[Stain deconvolution](https://youtu.be/fHbsWGF47Zg) <br>
[Pixel classification (epithelium/stroma for H&E images)](https://youtu.be/kGvZRBEeqI0) <br>
[Nuclei segmentation (watershed) and DAB positive cells](https://youtu.be/ASY7sepHHyw) <br>
[Nuclei segmentation (stardist) and DAB positive cells (thresholding)](https://youtu.be/3Dsq9NljCVs) <br>
[Visualization of fluorescence images](https://youtu.be/BIF9KHu1RLk) <br>
[Pixel classification (epithelium/stroma for fluorescence images)](https://youtu.be/VbcTe7bawRs) <br>
[Nuclei segmentation (stardist)](https://youtu.be/GBFBVT2stMQ) <br>
[Object classification for marker identification](https://youtu.be/HI2-BNpjKmo) <br>
[Cellpose installation for QuPath and Fiji](https://youtu.be/A_PW_N0np9A) <br>
[Cellpose GPU installation for QuPath and Fiji](https://youtu.be/yx4w4E4v1uM) <br>
[Muscle fiber segmentation and muscle fiber type classification with QuPath](https://youtu.be/C0R1cz2hWQg) <br><br>
Analysis of multiplexed whole slide images with QuPath and Cytomap (MIFOBIO 2023 workshop): <br>
[Load and visualize data with QuPath](https://youtu.be/sAFZtuM_waQ) <br>
[Tissue and nuclei segmentations](https://youtu.be/aOztik5vabw) <br>
[Export measurements from QuPath](https://youtu.be/xRqus2Q7IRE) <br>
[Importing measurements into Cytomap](https://youtu.be/c2Tn2qSl_sA) <br>
[Unsupervised cell clustering with Cytomap](https://youtu.be/U5fOWLiyVuw) <br>
[Importing Cytomap clustering into QuPath](https://youtu.be/jud_PSzBWUw) <br>

# Citations
If you use QuPath, please cite: <br> 
Bankhead et al. (2017): [QuPath: Open source software for digital pathology image analysis](https://doi.org/10.1038/s41598-017-17204-5) <br><br> 
If you use Stardist, please cite : <br>
Schmidt et al. (2018): [Cell detection with Star-convex polygons](https://arxiv.org/abs/1806.03535) <br><br> 
If you use the model for nuclei segmentation in the script folder (stardist_model_1_channel.pb), please cite the following papers from which data was used to train the model: <br> 
Greenwald et al. (2022): [Whole-cell segmentation of tissue images with human-level performance using large-scale data annotation and deep learning](https://doi.org/10.1038/s41587-021-01094-0) <br>
Pécot et al. (2022): [Deep learning tools and modeling to estimate the temporal expression of cell cycle proteins from 2D still images](https://doi.org/10.1371/journal.pcbi.1009949) <br>
Pécot et al. (2022): [A deep learning segmentation strategy that minimizes the amount of manually annotated images](https://doi.org/10.12688/f1000research.52026.2)

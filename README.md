# Whole-Slide Image Analysis and Quantitative Pathology with QuPath

This is a 2-day workshop focusing on the quantitative analysis of H&E and fluorescence whole-slide images with QuPath.

Participants learn how to annotate regions, to perform stain deconvolution, to segment nuclei (watershed-based method and pretrained deep learning models), to classify pixels and cells (shallow machine learning), to identify fluorescent markers associated with cells (intensity thresholding and shallow machine learning) and to characterize cell sub-populations (region clustering). The first day focuses on H&E and H-DAB whole-slide images. The second day focuses on multiplexed fluorescence whole-slide images.

A pdf introduces pyramidal representation of images and describes the background required to understand the different analyses conducted through the workshop. Links to video tutorials are available in the pdf to see how each "hands on" session can be done. The images used in these sessions are available at https://zenodo.org/record/6391629. The scripts, Stardist model and segmentations for the multiplexed image as a geojsaon file are available in the script folder.

# Citations
Please cite this paper if you use QuPath: <br> 
Bankhead et al. (2017): [QuPath: Open source software for digital pathology image analysis](https://doi.org/10.1038/s41598-017-17204-5) <br>
If you use Stardist, please cite : <br> 
Schmidt et al. (2018): [Cell detection with Star-convex polygons](https://arxiv.org/abs/1806.03535) <br>
If you use the model for nuclei segmentation in the script folder (), please cite the following papers from which data was used to train the model : <br> 
Greenwald et al. (2021): [Whole-cell segmentation of tissue images with human-level performance using large-scale data annotation and deep learning](https://doi.org/10.1038/s41587-021-01094-0) <br>
Pécot et al. (2022): [Deep learning tools and modeling to estimate the temporal expression of cell cycle proteins from 2D still images](https://doi.org/10.12688/f1000research.52026.2) <br>
Pécot et al. (2022): [A deep learning segmentation strategy that minimizes the amount of manually annotated images](https://doi.org/10.12688/f1000research.52026.2)

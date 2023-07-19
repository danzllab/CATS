## Content:
The code consists of 4 folders, each having a jupyter notebook and test data.
All instructions are contained in the notebooks.
1) [Identification and segmentation of pSCRs guided by immunostaining for synaptic marker](./1_Identification%20and%20segmentation%20of%20pSCRs%20guided%20by%20immunostaining%20for%20synaptic%20marker/)
2) [MFB quantification](./2_MFB%20quantification/)
3) [Deep-Learning based prediction of synaptic location](./3_Deep-Learning%20based%20prediction%20of%20synaptic%20location/)
4) [Reconstruction of a local synaptic input field](./4_Reconstruction%20of%20a%20local%20synaptic%20input%20field/)

## Installation:
Clone the repository:
```
   git clone https://github.com/danzllab/CATS.git
```
### Option 1:
1) Open Anaconda Prompt
2) Navigate to the folder with the code:
```
   cd CATS
   cd cocats_image-analysis
```

3) Run the following commands:
```
   conda env create -f environment.yml
   conda activate cats-test-env
```
4) Run: 
```
   jupyter-lab
```

### Option 2:
1) Open Anaconda Prompt
2) Run the following commands:
```
   conda create -y -n cats-test-env -c conda-forge python=3.9
   conda activate cats-test-env
   pip install "napari[pyqt5]"
   pip install open3d
   pip install matplotlib
```
3) Navigate to the folder with the code:
```
   cd CATS
   cd cocats_image-analysis
```
4) Run: 
```
   jupyter-lab
```


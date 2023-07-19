# rCATS_scripts - Noise2Void on BigStitcher/N5 projects
### Stitching with BigStitcher
BigStitcher uses XML to store metadata and all estimated stichting transforms together with a backend data format to store the actual image or volume binary data. Currently BigStitcher supports [HDF5](https://www.hdfgroup.org/solutions/hdf5/) and [N5](https://github.com/saalfeldlab/n5) as backend data format. For efficient processing, we chose N5 since it supports chunked data, Gaussian Pyramid resolution levels and - importantly - allows parallel write access. This enables the use of high-performance cluster processing.

### Denoising with Noise2Void
Noise2void uses a self-supervised deep-learning paradigm. Hence, it requires a training and a prediction step. The output of the network is the denoised image.

In the training phase image patches of user specified size and quantity are sampled randomly from across the entire image. After training Noise2Void on the patches the neural network weights of the network, which achieved lowest validation error are stored to disk.

During prediction the restored network is used to predict the denoised image. This can be done in parallel, where each image chunk (called *setup* in BigStitcher).

All parameters for the training and prediction are given as [CAREless](https://github.com/sommerc/CAREless) config .json file.

### Training
Training Noise2Void on BigStitcher XML/N5 project files can be done by the [n2v_train_n5.py](n2v_train_n5.py) command line interface.

```bash
usage: n2v_train_n5.py [-h] --n2v_project N2V_PROJECT [-s SETUP_IDS] n5_file

CAREless Noise2Void: command line script for training n2v from BS-BDV N5 images

positional arguments:
  n5_file               N5 File to process with Noise2Void

optional arguments:
  -h, --help            show this help message and exit
  --n2v_project N2V_PROJECT
                        CAREless Noise2Void project file (.json)
  -s SETUP_IDS, --setup_ids SETUP_IDS
                        Select Setup ids by slicing (default all = ':')
```


### Prediction
Prediction with a trained Noise2Void model on BigStitcher XML/N5 project files can be done by the [n2v_predict_n5.py](n2v_predict_n5.py) command line interface.

```bash
usage: n2v_predict_n5.py [-h] [--ntiles NTILES NTILES NTILES] [--n_threads N_THREADS] [-i] -s SETUP_IDS
                         [SETUP_IDS ...] [-o OUT_PATH]
                         model_path n5_file

CAREless Noise2Void: command line script for predicting new BS-BDV N5 images

positional arguments:
  model_path            Path to the Noise2Void model folder
  n5_file               N5 File to process with Noise2Void

optional arguments:
  -h, --help            show this help message and exit
  --ntiles NTILES NTILES NTILES
                        Number of tiles per dimension (Z)YX used to predict each image chunk.
  --n_threads N_THREADS
                        Number of threads used for writing N5
  -i, --invert          Invert the denoised output.
  -s SETUP_IDS [SETUP_IDS ...], --setup_ids SETUP_IDS [SETUP_IDS ...]
                        List of Setup ids to predict
  -o OUT_PATH, --out_path OUT_PATH
                        Path to output file. If not specified, suffix '_n2v.n5' is used
```


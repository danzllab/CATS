# rCATS_scripts - Convert BigStitcher Fused Images to WebKnossos

### Stitching with BigStitcher
BigStitcher uses XML to store metadata and all estimated stitching transforms together with a backend data format to store the actual image or volume binary data. Currently, BigStitcher only supports [HDF5] for the fusion of the stitched tiles into a chunked and pyramidal image format.

### Converting fused BDV/HDF5 to WebKnossos format
[WebKnossos](https://webknossos.org/) enables collaborative annotations of objects and objects skeletons via a convenient web-interface. WebKnossos uses its own image data format. The Jupyter notebook [convert_to_webknoss_bdv_post_uint8.ipynb](convert_to_webknoss_bdv_post_uint8) converts a given BDV/HDF5 images containing the fused image after stitching into the native WebKnossos format. In addition, it can scale the dynamic range of each channel by providing a list of minimum and maximum values. The conversion process uses the [WebKnossos Python API](https://pypi.org/project/webknossos/).

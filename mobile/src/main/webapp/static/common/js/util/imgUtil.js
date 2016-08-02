/**
 * Created by tianyi on 16/1/5.
 */
//plupload中为我们提供了mOxie对象(https://github.com/moxiecode/moxie/wiki/API)
function previewImage(file, callback) {
    if (!file || !/image\//.test(file.type)) return; //确保文件是图片
    if (file.type == 'image/gif') {//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function () {
            callback(fr.result);
            fr.destroy();
            fr = null;
        };
        fr.readAsDataURL(file.getSource());
    } else {
        var preloader = new mOxie.Image();
        preloader.onload = function () {
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
            callback && callback(imgsrc);
            preloader.destroy();
            preloader = null;
        };
        preloader.load(file.getSource());
    }
}
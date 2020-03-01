## 多线程
### 1.继承Thread父类
- 步骤<br>
    1.继承Thread<br>
    2.重写Run方法<br>
    3.调动start方法启动多线程
    
### 多线程下载图片Demo
```java
//继承Thread
public class TestThread extends Thread {
    private String url;
    private String name;

    public TestThread(String url,String name){
        this.url=url;
        this.name=name;
    }
    //重写run方法
    @Override
    public void run() {
        webDownloader webDownloader=new webDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名："+name);
    }

    //主线程
    public static void main(String[] args) {
        TestThread t1=new TestThread("https://dss0.baidu.com/73t1bjeh1BF3odCf/it/u=422137604,301522349&fm=85&s=C5973F9E8031718A0049D95E030050F3","1.png");
        TestThread t2=new TestThread("https://dss0.baidu.com/73t1bjeh1BF3odCf/it/u=422137604,301522349&fm=85&s=C5973F9E8031718A0049D95E030050F3","2.png");
        TestThread t3=new TestThread("https://dss0.baidu.com/73t1bjeh1BF3odCf/it/u=422137604,301522349&fm=85&s=C5973F9E8031718A0049D95E030050F3","3.png");
        t1.start();
        t2.start();
        t3.start();
    }
}
//下载图片的方法
class webDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io异常，downloader方法出现异常");
        }
    }
}
```
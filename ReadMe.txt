使用する場合はsrc/worksに画像ファイル,jarと同じディレクトリに作品を入れる。
理由は、プログラム中のリソースを分離するため。

ビルド方法は"build.fxbuild"の右上にある"Generate ant build.xml and run"を押す。
これによってbuild/dist/**.jarが生成できる。
"build.fxbuild"のパラメーターを変更することでファイル名などを変更できる。

work.xmlは
<work>-作品
		<name>作品名</name>
		<creator作成者</creator>
		<description>作品の説明</description>
		<path>ディレクトリ名</path>
</work>
のように記述する。

nameとpathが違うのは作品名にファイル名として用いることができない文字が含まれているかもしれないため。
特に問題ない場合は同じ。

サンプル画像は3枚とする。
MenuController#initField()#240あたりで追加可能

src直下のworksはリソースファイルなので画像ファイルのみ格納する

StringUtil#LAUNCHER_NAMEで画面左上のタイトル変更

実際に操作するファイルは直下のworksに入れる
スクリーンショットはScreenshotファイルに入れる
作品名にJavaProjectに入れられない文字が含まれる場合は適当に加工してその文字をxmlのpathに指定する


使用する場合はsrc/worksに画像ファイル,jarと同じディレクトリに作品を入れる。
理由は、プログラム中のリソースを分離するため。

ビルド方法は"build.fxbuild"の右上にある"Generate ant build.xml and run"を押す。
これによってbuild/dist/**.jarが生成できる。
"build.fxbuild"のパラメーターを変更することでファイル名などを変更できる。

work.xmlは
<work>-作品
		<name>作品名</name>
		<creator作成者</creator>
		<image>サンプル画像のパス</image>
		<description>作品の説明</description>
		<path>作品のパス</path>
</work>
のように記述する。

作品名とパスが違うのは作品名にファイル名として用いることができない文字が含まれているかもしれないため。

サンプル画像は3枚とする。
MenuController#initField()で変更可能



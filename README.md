# Gradle
## Gradle Lifecycle
<img src="/images/gradle_lifecycle.png" style="width:1100px; height: 720px;" title="" alt=""/>
<img src="/images/gradle_lifecycle_2.png" style="width:1000px; height: 500px;" title="" alt=""/>
<p>Каждый проект состоит из тасок <code>Project -> [Task1, Task2]</code>, которые в свою очередь состоят из набора
действий <code>Task -> [Action1, Action2]</code>, у которых вызвается метод <code>execute()</code>на фазе Execution.</p>

## Task graph & Properties
<p>Схематично task-graph выглядит так(ацкиличный граф, который параллельно может выполнять задачи, которые не зависят от
других задач):</p>
<img src="/images/task_graph.png" style="width:700px; height: 325px;" title="" alt=""/>

## Plugins
<p>Файлы типа .gradle можно передавать в build-файл через команду <code>apply from: 'script.gradle'</code>.</p>
<p>Либо можно написать класс своего кастомного плагина <code>class CustomPlugin implements Plugin<Project> </code>, у 
которого нужно переопредилить 1 метод <code>void apply (Project project)</code>. И также через 
<code>apply plugin: CustomPlugin.class</code> передаем наш плагин.</p>
<p>Плагины можно подключать через блок <code>plugins{id 'plugin_id'}</code>. Кастомные плагины можно засунуть в buildSrc
и дать им id, чтобы их можно было подключать через блок, указанный выше.</p>

## Java Application Lifecycle
<p><code>gradle tasks</code> - посмотреть все доступные таски в нашем проекте.</p>
<p><code>compileJava</code> - компилирует наши Java классы.</p>
<p><code>processResources</code> - компилирует ресуры из директории /main/java/resources.</p>
<p><code>classes</code> - выполняет <code>compileJava, processResources</code>.</p>
<p><code>jar</code> - собирает наши скомпилированные файлы в какой-то артефакт: <code>jar, war</code>. Артефакт можно
найти в папке <code>/build/libs/project_name.jar|war</code></p>
<p><code>assemble</code> - вызывает все предыдущие сверху таски.</p>
<p><code>compileTestJava</code> - компилирует наши тестовые Java классы.</p>
<p><code>processTestResources</code> - компилирует ресуры из директории /test/java/resources.</p>
<p><code>testClasses</code> - выполняет <code>compileTestJava, processTestResources</code>.</p>
<p><code>test</code> - запускает наши тесты.</p>
<p><code>check</code> - проверяет выполняемсость наших тестов и генерирование отчетов по тестам.</p>
<p><code>build</code> - запускает все вышеперечисленные таски.</p>
<p><code>clean</code> - удаляет директорию build(чистит кэш).</p>
<p><code>buildNeeded</code> - билдит все проекты, от которых зависит наш текущий проект.</p>
<p><code>buildDependents</code> - наоборот билдит все прокты, которые зависят от нашего текущего проекта.</p>
<p>Указать директорые новых классов Java или файлов resources можно сделать следующим образом:</p>
<pre>
sourceSets {
    main {
        java {
            srcDir("$buildDir")
        }
        resources {
        }
    }
    test {
    }
}
</pre>

## Dependency Management
### Repositories

<p><code>repositories {}</code> в ней указываются источники поиска ваших зависимостей.</p>
<p><code>maven {}</code> указывается для своих репозиториев на своих серверах. Сначала gradle будет искать нужные
зависимости в <code>mavenLocal</code>, т.к. мы указали его раньше чем <code>maven{...}</code>. И все эти зависимости
он кэширует в своей локальной папке.</p>
<img src="/images/repositories.png" style="width:1100px; height: 510px;" title="" alt=""/>

<p><code>implementation</code> - используется для подключения зависимостей в нашем исходном коде.</p>
<p><code>compileOnly</code> - используется только во время комплияций. Во время выполнения она(зависимость) нам не 
нужна.</p>
<p><code>compileClassPath</code> - включает в себя <code>compileOnly, implementation</code>.</p>
<p><code>annotationProcessor</code> - используется для обработки наших аннотаций.</p>
<p><code>runtimeOnly</code> - аналог <code>compileOnly</code>, но она доступна только во время выполнения. Во время 
компиляции она нам не нужна. Например, драйверы БД.</p>
<p><code>runtimeClasspath extends runtimeOnly, implementation</code> - включает в себя <code>runtimeOnly, 
implementation</code>.</p>
<p><code>testImplementation extends implementation</code> - используется для подключения зависимостей в нашем исходном 
коде, которые лежат в папке test.</p>
<p><code>testCompileOnly</code> - используется только во время комплияций тестов. Во время выполнения она(зависимость) нам не 
нужна.</p>
<p><code>testCompileClassPath extends testCompileOnly, testImplementation</code> - включает в себя 
<code>testCompileOnly, testImplementation</code>.</p>
<p><code>testRuntimeOnly extends runtimeOnly</code> - аналог <code>testCompileOnly</code>, но она доступна только во
время выполнения. Во время компиляции она нам не нужна.</p>
<p><code>testRuntimeClasspath extends testRuntimeOnly, testImplementation</code> - включает в себя 
<code>testRuntimeOnly, testImplementation</code>.</p>
<p><a href="https://docs.gradle.org/current/userguide/java_plugin.html#java_plugin">Подробная документация</a>.</p>

### Configurations
<p>С помощью блока <code>configurations{...}</code> можно создавать свои конфигурации.</p>

### Транзитивные зависимости
<p><i>Транзитивные зависимости</i> - зависимости, которые были подключены неявно. Например, при подключении 
spring-webmvc подключается spring-core и т.п.</p>
<p>Для исключения транзитивных зависимостей у <code>implementation</code> можно вызвать <code>exclude{}</code> и 
перечислить там зависимости для удаления. Либо можно указать <code>transitive = false</code></p>
<p><code>platform()</code> резолвит конфликты зависимостей. По факту BOM -  это просто xml-файл со всеми зависимостями и
версиями, которые совместимы друг с другом.</p>

## JAR
### Fat JAR
<p>Для того, чтобы запустить jar-файл со всеми зависимости, есть вариант получить все зависимости и разархивировать их.
</p>
<pre>
jar {
    manifest {
        attributes 'Main-Class': 'ru.atom.gradle.HelloWorld'
    }
    duplicatesStrategy(DuplicatesStrategy.EXCLUDE)
    from(configurations.runtimeClasspath.files.collect {
        zipTree(it)
    })
}
</pre>

### JAR 2
<p>Пишем таску, которая копирует все зависимости в папку lib. Затем делаем таску jar, зависимой от нашей таски
копирования зависимостей, и добавляем аттрибут Class-Path.</p>
<pre>
task copyAllDependencies(type: Copy) {
    from(configurations.runtimeClasspath.files)
    into("$buildDir/libs/lib")
}

jar {
    dependsOn(copyAllDependencies)
    def jars = configurations.runtimeClasspath.files.collect{"lib/$it.name"}
    manifest {  
        attributes 'Main-Class': 'ru.atom.gradle.HelloWorld',
                    'Class-Path': jars.join(' ')
    }
}
</pre>

## Multiproject builds
<p>Применяя <code>plugin {'java-library'}</code> и указывая в <code>dependencies {api project (project_name)}</code>,
проекты, которые включают в себя эту библиотеку, будут и включать эту зависимость. <a href="database/build.gradle">
Пример</a>.</p>
<p>С помощью директивы <code>allprojects{}</code> можно настроть общую конфигурацию для всех проектов. А с помощью
<code>subprojects{} для подпроектов.</code></p>
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

## Gradle Tasks
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
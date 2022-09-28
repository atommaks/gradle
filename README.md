# Gradle
## Gradle Lifecycle
<img src="/images/gradle_lifecycle.png" style="width:1100px; height: 720px;" title="" alt=""/>
<img src="/images/gradle_lifecycle_2.png" style="width:1000px; height: 500px;" title="" alt=""/>
<p>Каждый проект состоит из тасок <code>Project -> [Task1, Task2]</code>, которые в свою очередь состоят из набора
действий <code>Task -> [Action1, Action2]</code>, у которых вызвается метод <code>execute()</code>на фазе Execution.</p>
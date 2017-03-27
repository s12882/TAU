package pl.edu.pjatk.tau.lab4;


import org.jbehave.core.Embeddable;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;


@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyControls = AlarmRingEmbedder.MyStoryControls.class, storyLoader = AlarmRingEmbedder.MyStoryLoader.class, storyReporterBuilder = AlarmRingEmbedder.MyReportBuilder.class,
        parameterConverters = { AlarmRingEmbedder.MyDateConverter.class })
@UsingEmbedder(embedder = Embedder.class,
        generateViewAfterStories = false,
        ignoreFailureInStories = true,
        ignoreFailureInView = true)
@UsingSteps(instances = { AlarmRingSteps.class })
public class StepsEmbeddable implements Embeddable {
    private Embedder embedder;

    public void useEmbedder(Embedder embedder) {
        this.embedder = embedder;
    }

    @Override
    @Test
    public void run() throws Throwable {
        embedder.runStoriesAsPaths(new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(),
                asList("**/*.story"), asList("")));
    }
}

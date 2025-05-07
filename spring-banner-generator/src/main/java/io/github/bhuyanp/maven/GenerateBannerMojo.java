package io.github.bhuyanp.maven;

import io.github.bhuyanp.maven.theme.ThemePreset;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Mojo(name = "generateBanner", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
public class GenerateBannerMojo extends BannerMojoBase implements BannerTextUtil {
    public static final String OUTPUT_DIRECTORY_DEFAULT_VALUE = "${project.build.outputDirectory}";
    public static final String BANNER_FILE_NAME = "banner.txt";

    @Parameter(defaultValue = OUTPUT_DIRECTORY_DEFAULT_VALUE)
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String bannerWCaption = getBannerWCaption(project, bannerText, Arrays.asList(bannerFonts), captionText, themePreset);
        wtiteFile(bannerWCaption);
    }

    private void wtiteFile(String bannerWCaption) throws MojoExecutionException {
        final Path bannerFile = outputDirectory.toPath().resolve(BANNER_FILE_NAME);
        if (outputDirectory.exists() || outputDirectory.mkdirs()) {
            try {
                Files.writeString(bannerFile, bannerWCaption);

            } catch (IOException e) {
                throw new MojoExecutionException("Failed to create banner file " + outputDirectory);
            }
        } else {
            throw new MojoExecutionException("Failed to create output directory " + outputDirectory);
        }
    }
}
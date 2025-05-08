package io.github.bhuyanp.maven;

import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Mojo to write banner file to output build folder which then gets bundled inside
 * spring boot application jar.
 */
@Mojo(name = "generateBanner", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
public class GenerateBannerMojo extends BannerMojoBase implements BannerTextUtil {
    private static final String BANNER_FILE_NAME = "banner.txt";


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String bannerWCaption = getBannerWCaption(project, bannerText, Arrays.asList(bannerFonts), captionText, themePreset);
        writeFile(bannerWCaption);
    }

    private void writeFile(String bannerWCaption) throws MojoExecutionException {
        File outputDirectory = new File(project.getBuild().getOutputDirectory());
        if (outputDirectory.exists() || outputDirectory.mkdirs()) {
            try {
                final Path bannerFile = outputDirectory.toPath().resolve(BANNER_FILE_NAME);
                Files.writeString(bannerFile, bannerWCaption);
            } catch (IOException e) {
                throw new MojoExecutionException("Failed to create banner file " + outputDirectory);
            }
        } else {
            throw new MojoExecutionException("Failed to create output directory " + outputDirectory);
        }
    }
}
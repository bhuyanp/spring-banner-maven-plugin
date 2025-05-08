package io.github.bhuyanp.maven;

import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Base mojo abstract class with common configurations across all mojo available
 * in this plugin. All configurations are optional with sensible defaults for each
 * configuration.
 */

public abstract class BannerMojoBase extends AbstractMojo implements BannerTextUtil {

    /**
     * Maven project injected to obtain project name and version etc
     */
    @Parameter(defaultValue = "${project}", readonly = true)
    MavenProject project;

    /**
     * Optional banner text. Project name is used as banner text if not provided.
     */
    @Parameter(property = "bannerText")
    String bannerText;

    /**
     * One or more fonts for the banner. For more than one font, plugin will randomly cycle through
     * them. When no fonts provided, plugin randomly cycles through the predefined list of default fonts.
     * <br/>
     * <br/>
     * See banner text util {@link BannerTextUtil} for the list of default fonts.
     */
    @Parameter(property = "bannerFonts")
    String[] bannerFonts;

    /**
     * Caption text appears under the banner with useful information about the project.
     * <br/>
     * <br/>
     * See Banner text utll {@code  BannerTextUtil} for the default caption when none provided.
     *
     */
    @Parameter(property = "captionText",  defaultValue = CAPTION_DEFAULT_VALUE)
    String captionText;

    /**
     * Theme presets for banner and caption text color, background color and other effects.
     * <br/>
     * <br/>
     * DARK(default) - Suite for dark terminal and IDE themes<br/>
     * LIGHT - Suite for IDE's with light theme<br/>
     * SURPRISE_ME - All color and effects are randomized. A cool option to show off your application console.
     *
     */
    @Parameter(property = "themePreset", defaultValue = "DARK")
    String themePreset;

}

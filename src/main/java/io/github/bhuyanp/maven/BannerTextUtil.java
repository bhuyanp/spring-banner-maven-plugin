package io.github.bhuyanp.maven;


import io.github.bhuyanp.maven.figlet.FigletBannerRenderer;
import io.github.bhuyanp.maven.theme.TextPadding;
import io.github.bhuyanp.maven.theme.Theme;
import io.github.bhuyanp.maven.theme.ThemeConfig;
import io.github.bhuyanp.maven.theme.ThemePreset;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.github.bhuyanp.maven.ansi.Ansi.colorize;

/**
 * Interface various utility function to generate banner and caption text
 * with theme.
 */
public interface BannerTextUtil {

    String CAPTION_DEFAULT_VALUE =  """
                Version                 : ${project.version}
                Spring Boot Version     : ${project.parent.version}
                JDK Version             : ${java.version}
                """;


    default String getBannerWCaption(MavenProject project, String bannerText, List<String> bannerFonts, String captionText, String themePreset) {
        String bannerFont;
        if (bannerFonts.size() > 1) {
            bannerFont = bannerFonts.get(new Random().nextInt(bannerFonts.size()));
        } else if (bannerFonts.size() == 1) {
            bannerFont = bannerFonts.get(0);
        } else {
            bannerFont = DEFAULT_FONTS[new Random().nextInt(DEFAULT_FONTS.length)];
        }
        return getBannerWCaption(project, bannerText, bannerFont, captionText, themePreset);
    }


    default String getBannerWCaption(MavenProject project, String bannerText, String bannerFont, String captionText, String themePresetStr) {
        bannerText = null==bannerText||bannerText.isBlank()?capitalizeProjectName(project.getName()): bannerText;

        ThemePreset themePreset = ThemePreset.valueOf(themePresetStr);
        ThemeConfig bannerTheme = themePreset.getTheme().getBannerTheme();
        ThemeConfig captionTheme = themePreset.getTheme().getCaptionTheme();

        bannerText = getBanner(bannerFont, bannerText, bannerTheme);
        captionText = getCaption(captionText, captionTheme);
        if (themePreset==ThemePreset.SURPRISE_ME) {
            System.out.println("bannerTheme = " + bannerTheme);
            if (!captionText.isBlank()) {
                System.out.println("captionTheme = " + captionTheme);
            }
        }
        String result = captionText.isBlank()
                ? bannerText
                : bannerText + System.lineSeparator() + System.lineSeparator() + captionText;
        return System.lineSeparator() + result + System.lineSeparator();

    }

    String DEFAULT_SPACING = " ";

    private String getBanner(String font, String text, ThemeConfig bannerTheme) {
        if (text.isBlank()) return text;
        System.out.println(System.lineSeparator()+"Banner Font: " + font);
        String banner = FigletBannerRenderer.SINGLETON.render(font, text);

        //Apply padding only when back color is present
        if (bannerTheme.hasBackColor()) {
            TextPadding textPadding = Theme.getBannerPadding(font);
            banner = textPadding.apply(banner);
            System.out.println("Banner Paddings: " + textPadding);
        }

        banner = banner.lines()
                .map(line -> colorize(line, bannerTheme))
                .collect(Collectors.joining(System.lineSeparator()));
        return banner;
    }

    default String getCaption(String caption, ThemeConfig captionTheme) {
        if (caption.isBlank()) return caption;
        //For no background, padding not needed for captions
        boolean addPadding = captionTheme.hasBackColor();
        int biggestCaptionLine = caption.lines().map(String::length)
                .max(Integer::compareTo).get();
        caption = caption.lines()
                .map(String::trim)
                .map(line -> line + DEFAULT_SPACING.repeat(biggestCaptionLine - line.length()))
                .collect(Collectors.joining(System.lineSeparator()));
        if (addPadding) {
            caption = new TextPadding(1, 2, 1, 2).apply(caption);
        } else {
            caption = caption.lines().map(line->"| "+line).collect(Collectors.joining(System.lineSeparator()));
        }
        return colorize(caption, captionTheme);
    }

    /**
     *
     * @param projectName Name of the project
     * @return The capitalized project name and dashes replaces with spaces
     */
    default String capitalizeProjectName(String projectName) {
        if(null==projectName || projectName.isBlank()) throw new IllegalArgumentException("Project name cant be blank or null");
        return Arrays.stream(projectName.replace('-', ' ').split(" ")).map(name ->
                        name.substring(0, 1).toUpperCase() + name.substring(1))
                .collect(Collectors.joining(" "));
    }


    String[] DEFAULT_FONTS = {
            "3d",
            "4max",
            "ansiregular",
            "ansishadow",
            "banner3_d",
            "banner4",
            "bigmoneyne",
            "block",
            "bolger",
            "calvins",
            "colossal",
            "cyberlarge",
            "elite",
            "georgia11",
            "lean",
            "epic",
            "lineblocks",
            "nancyj",
            "poison",
            "starwars",
            "puffy",
            "soft",
            "standard",
            "usaflag",
            "usaflag",
            "usaflag",
            "whimsy"};

    String LINE_SEPARATOR = System.lineSeparator()+"-".repeat(30);

}

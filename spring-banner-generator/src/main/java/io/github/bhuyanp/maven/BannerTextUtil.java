package io.github.bhuyanp.maven;


import io.github.bhuyanp.maven.figlet.FigletBannerRenderer;
import io.github.bhuyanp.maven.theme.TextPadding;
import io.github.bhuyanp.maven.theme.Theme;
import io.github.bhuyanp.maven.theme.ThemeConfig;
import io.github.bhuyanp.maven.theme.ThemePreset;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.github.bhuyanp.maven.ansi.Ansi.colorize;

public interface BannerTextUtil {

    String CAPTION_DEFAULT_VALUE =  """
                Version                 : ${project.version}
                Spring Boot Version     : ${project.parent.version}
                JDK Version             : ${java.version}
                """;


    default String getBannerWCaption(String bannerText, List<String> bannerFonts, String captionText, ThemePreset themePreset) {
        String bannerFont = "usaflag";
        if (bannerFonts.size() > 1) {
            bannerFont = bannerFonts.get(new Random().nextInt(bannerFonts.size()));
        } else if (bannerFonts.size() == 1) {
            bannerFont = bannerFonts.get(0);
        }
        return getBannerWCaption(bannerText, bannerFont, captionText, themePreset);
    }

    default String getBannerWCaption(String bannerText, String bannerFont, String captionText, ThemePreset themePreset) {

        return getBannerWCaption(bannerFont, bannerText, themePreset.getTheme().getBannerTheme(), captionText,  themePreset.getTheme().getCaptionTheme(), themePreset == ThemePreset.SURPRISE_ME);
    }

    private String getBannerWCaption(String bannerFont, String bannerText, ThemeConfig bannerTheme, String captionText, ThemeConfig captionTheme, boolean printConfig) {
        bannerText = getBanner(bannerFont, bannerText, bannerTheme);
        captionText = getCaption(captionText, captionTheme);
        if (printConfig) {
            System.out.println("  bannerTheme = " + bannerTheme);
            if (!captionText.isBlank()) {
                System.out.println("  captionTheme = " + captionTheme);
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
        System.out.println("  Banner Font: " + font);
        String banner = FigletBannerRenderer.SINGLETON.render(font, text);

        TextPadding textPadding = Theme.getBannerPadding(font);

        //For no background banners no left/right padding needed
        if (!bannerTheme.hasBackColor()) {
            textPadding = new TextPadding(textPadding.getTop(), 0, textPadding.getBottom(), 0);
        }

        System.out.println("  Banner Paddings: " + textPadding);
        banner = textPadding.apply(banner);
        banner = banner.lines()
                .map(line -> colorize(line, bannerTheme))
                .collect(Collectors.joining(System.lineSeparator()));
        return banner;
    }

    String SPRING_BOOT_VERSION = "${spring-boot.version}";


    default String getCaption(String caption, ThemeConfig captionTheme) {
        if (caption.isBlank()) return caption;
        //For no background, padding not needed for captions
        boolean addPadding = captionTheme.hasBackColor();
        int biggestLineLength = caption.lines().map(line -> {
                    if (line.contains(SPRING_BOOT_VERSION)) {
                        return line.length() - SPRING_BOOT_VERSION.length() + 5;
                    } else {
                        return line.length();
                    }
                })
                .max(Integer::compareTo).get()+1;
        caption = caption.lines()
                .map(line -> {
                    if (line.contains(SPRING_BOOT_VERSION)) {
                        return line + DEFAULT_SPACING.repeat(biggestLineLength - (line.length() - SPRING_BOOT_VERSION.length() + 5));
                    } else {
                        return line + DEFAULT_SPACING.repeat(biggestLineLength - line.length());
                    }
                })
                .collect(Collectors.joining(System.lineSeparator()));
        if (addPadding) {
            caption = new TextPadding(1, 2, 1, 3).apply(caption);
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

    List<String> DEFAULT_FONTS = List.of(
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
            "whimsy");

}

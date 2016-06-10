package in.wavelabs.idn.DataModel.media;

import java.util.List;

import in.wavelabs.idn.DataModel.validation.MessagesApiModel;

/**
 * Created by vineelanalla on 29/01/16.
 */
public class MediaApiModel extends MessagesApiModel{
    public String getExtension() {
        return extension;
    }

    public List<MediaFileDetailsApiModel> getMediaFileDetailsList() {
        return mediaFileDetailsList;
    }

    public String getSupportedsizes() {
        return supportedsizes;
    }

    private String extension;
    private List<MediaFileDetailsApiModel> mediaFileDetailsList;
    private String supportedsizes;
    public MessagesApiModel getErrors() {
        return errors;
    }

    private MessagesApiModel errors;
}

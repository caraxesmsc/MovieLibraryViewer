

def read_video_metadata(file_path):
    try:
        video = VideoFileClip(file_path)
        metadata = video.reader.metadata

        # Extract metadata attributes
        name = metadata.get("title", "Unknown")
        date_modified = metadata.get("date", "Unknown")
        genre = metadata.get("genre", "Unknown")
        rating = metadata.get("rating", "Unknown")
        tags = metadata.get("comment", "Unknown")
        contributing_artists = metadata.get("artist", "Unknown")

        print("Name:", name)
        print("Date Modified:", date_modified)
        print("Genre:", genre)
        print("Rating:", rating)
        print("Tags:", tags)
        print("Contributing Artists:", contributing_artists)

    except Exception as e:
        print("Error reading video metadata:", str(e))

if __name__ == "__main__":
    video_file_path = "C:\My_Data\Movies\500 Days Of Summer 2009.mp4"
    read_video_metadata(video_file_path)

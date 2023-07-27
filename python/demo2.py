import subprocess
metadata=None
def get_video_metadata(file_path):
    try:
        cmd = [r'C:\Users\mdzhs\OneDrive\Documents\Programming Projects\MovieLibraryViewer\MovieLibraryViewer\python\exiftool.exe', file_path]
        result = subprocess.run(cmd, capture_output=True, text=True)
        metadata = result.stdout
        return metadata
    except FileNotFoundError as e:
        print("Error:", e)
        return None

# Usage example
video_file = r'C:\\My_Data\\Movies\\trial\\500 Days Of Summer 2009.mp4'
metadata = get_video_metadata(video_file)
if metadata:
    print(metadata)


desired_fields = ['File Name', 'File Modification Date/Time', 'Genre', 'Rating', 'Tags', 'Contributing Artists']
# Get the video file name without the extension
video_name = video_file.split("/")[-1].split(".")[0]

# Assuming metadata, desired_fields, and video_name are correctly defined

# Split metadata into an array of lines
metadata_lines = metadata.splitlines()

# Create an array to store the desired metadata lines
desired_metadata = []

# Loop through each line of metadata
for line in metadata_lines:
    for field in desired_fields:
        if line.startswith(field + ':'):
            metadata_value = line.split(":")[1].strip()  # Extract metadata value after the ':'
            desired_metadata.append(metadata_value)

# Create the text file and write the desired metadata
file_path = f"{video_name}.txt"
with open(file_path, 'w') as file:
    for line in desired_metadata:
        file.write(line + '\n')

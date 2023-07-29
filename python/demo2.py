import subprocess

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
video_file = r'C:\\My_Data\\Movies\\500 Days Of Summer 2009.mp4'
metadata = get_video_metadata(video_file)
if metadata:
    print(metadata)

desired_fields = ['File Name                       ', 'File Creation Date/Time         ', 'Genre                           ', 'Comment                         ', 'Category                        ', 'Artist                          ']
# Get the video file name without the extension
video_name = video_file.split("/")[-1].split(".")[0]

# Assuming metadata, desired_fields, and video_name are correctly defined

# Split metadata into an array of lines
metadata_lines = metadata.splitlines()

# Create an array to store the desired metadata values
desired_metadata_values = []

# Loop through each line of metadata
for line in metadata_lines:
    for field in desired_fields:
        if line.startswith(field + ':'):
            # Extract metadata value after the ':' and remove leading/trailing spaces
            metadata_value = line.split(":")[1].strip()
            desired_metadata_values.append(metadata_value)

# Create the text file and write the desired metadata values
file_path = f"{video_name}.txt"
with open(file_path, 'w') as file:
    for value in desired_metadata_values:
        file.write(value + '\n')

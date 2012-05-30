def java_archives
  @java_archives ||= Dir.glob("lib/**/*.jar")
end

def java_source_directories
  @java_source_directories ||= Dir.glob("*/src/main/java")
end

def java_sources
  return @java_sources if @java_sources
  @java_sources = []
  java_source_directories.each do |dir|
    Dir.glob("#{dir}/**/*.java").each do |java|
      @java_sources << java
    end
  end
  @java_sources
end

def java_classes
  @java_classes ||= Dir.glob("tmp/bin/**/*.class")
end

def bin_directory
  return @bin_directory if @bin_directory
  @bin_directory = File.expand_path("tmp/bin")
  Dir.mkdir @bin_directory unless File.exists?(@bin_directory)
  @bin_directory
end

def packages
  @packages ||= Dir.glob("pkg/**/*.jar").sort
end

def latest_package
  @latest_package ||= packages.last
end

desc "Compile sources"
task :compile do
  system "javac -classpath #{java_archives.join(':')} -sourcepath #{java_source_directories.join(':')} -d #{bin_directory} #{java_sources.join(' ')}"
end

desc "Package *.jar file"
task :package => :compile do
  jar = File.join("jquantlib-#{Time.now.to_s.scan(/\d+/).join}.jar")
  system "jar cf #{jar} -C #{bin_directory} ."
end

desc "Open console (Scala REPL)"
task :repl do
  system "scala -classpath #{java_archives.join(':')}:#{latest_package}"
end
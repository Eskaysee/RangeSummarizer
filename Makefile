BINDIR=./bin
SRCDIR=./src
DOCDIR=./doc
LIBDIR=./lib
TESTDIR=$(SRCDIR)

#get list of all java files in source directory
src_files=$(shell find $(SRCDIR) -name *java)
#gets list of binary class files from each source file (replaces .java with .class)
out_files=$(src_files:$(SRCDIR)/%.java=$(BINDIR)/%.class)


build: $(out_files)

$(BINDIR)/%.class: $(SRCDIR)/%.java
	javac -d $(BINDIR) -cp $(BINDIR):$(LIBDIR)/*:$(TESTDIR) $^

run: $(out_files)
	java -cp  $(BINDIR) Driver

clean:
	rm -f $(BINDIR)/*.class -r $(BINDIR)/numberrangesummarizer $(BINDIR)/test

docs:
	javadoc  -cp $(BINDIR) -d $(DOCDIR) $(SRCDIR)/*.java $(SRCDIR)/numberrangesummarizer/*.java

tests:
	java -cp $(BINDIR):$(LIBDIR)/* org.junit.runner.JUnitCore test.NumberRangeSummarizerTest

cleandocs:
	rm -rf $(DOCDIR)/*


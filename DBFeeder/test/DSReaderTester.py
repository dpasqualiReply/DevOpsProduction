from DatasetReader import DatasetReader

if __name__ == '__main__':
    moviesDS = DatasetReader.initWithFraction('datasets/movies.csv', 1.0, ',')

    for m in moviesDS.readPercentage()[:10]:
        print m